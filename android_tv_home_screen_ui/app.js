(function () {
  'use strict';

  // PUBLIC_INTERFACE
  function initTvHome() {
    /**
     * Builds the Android TV Home preview using mock data and enables D-pad navigation:
     * - Top nav focus row
     * - Hero actions (Play/Add/Info)
     * - Multiple horizontal carousels with Left/Right scrolling via translateX
     * - Enter triggers Play; Escape/Backspace closes Info or resets hero
     */

    // Mock data (use local images from assets/figmaimages)
    const mockItemsA = [
      { id: 'a1', title: 'Gladiator II', img: '../assets/figmaimages/figma_image_1_41.png', desc: 'Lucius must face the arena to restore Rome.' },
      { id: 'a2', title: 'Ex Machina', img: '../assets/figmaimages/figma_image_1_68.png', desc: 'An AI test spirals into a tense mind game.' },
      { id: 'a3', title: '2012', img: '../assets/figmaimages/figma_image_1_102.png', desc: 'The world collapses under cataclysmic events.' },
      { id: 'a4', title: 'Sing Street', img: '../assets/figmaimages/figma_image_1_85.png', desc: 'A teen forms a band to impress a girl.' },
      { id: 'a5', title: 'Ad Astra', img: '../assets/figmaimages/figma_image_1_119.png', desc: 'An astronaut journeys to find his father.' },
      { id: 'a6', title: 'Heroic Saga', img: '../assets/figmaimages/figma_image_1_24.png', desc: 'A bold tale of courage and fate.' },
      { id: 'a7', title: 'Night Shift', img: '../assets/figmaimages/figma_image_1_31.png', desc: 'Mysteries unravel after dark.' }
    ];
    const mockItemsB = [
      { id: 'b1', title: 'Ocean Deep', img: '../assets/figmaimages/figma_image_1_540.png', desc: 'Dive into the unseen world under the sea.' },
      { id: 'b2', title: 'The Last Stand', img: '../assets/figmaimages/figma_image_1_218.png', desc: 'A final fight against impossible odds.' },
      { id: 'b3', title: 'City Lights', img: '../assets/figmaimages/figma_image_1_10.png', desc: 'Neon dreams in a restless city.' },
      { id: 'b4', title: 'Orbit', img: '../assets/figmaimages/figma_image_1_8.png', desc: 'A journey beyond gravitational bounds.' },
      { id: 'b5', title: 'Far From Home', img: '../assets/figmaimages/figma_image_1_180.png', desc: 'Returning is harder than leaving.' }
    ];

    const state = {
      rows: [],
      // track translateX per row for horizontal scroll
      rowOffsets: [],
      // currently selected content for hero
      selected: null,
      focus: {
        area: 'nav', // 'nav' | 'hero' | 'rows' | 'overlay'
        rowIndex: 0,
        colIndex: 0,
        actionIndex: 0, // for hero actions
      }
    };

    // Build carousels
    const rowSections = document.querySelectorAll('.row');
    rowSections.forEach((row, idx) => {
      const dataset = idx === 0 ? mockItemsA : mockItemsB;
      const carousel = row.querySelector('.carousel');
      dataset.forEach((item, i) => {
        const card = document.createElement('button');
        card.className = 'card tv-focusable';
        card.type = 'button';
        card.setAttribute('role', 'listitem');
        card.setAttribute('tabindex', '-1');
        card.setAttribute('data-row', String(idx));
        card.setAttribute('data-col', String(i));
        card.setAttribute('aria-label', `${item.title}`);
        card.innerHTML = `
          <img src="${item.img}" alt="">
          <span class="label">${item.title}</span>
          <div class="shine"></div>
        `;
        // mousemove shine hint for desktop preview
        card.addEventListener('mousemove', (e) => {
          const r = card.getBoundingClientRect();
          const mx = (e.clientX - r.left) / r.width * 100;
          const my = (e.clientY - r.top) / r.height * 100;
          card.style.setProperty('--mx', `${mx}%`);
          card.style.setProperty('--my', `${my}%`);
        });
        card.addEventListener('focus', () => {
          // update hero preview when focused
          setHero(item);
          state.selected = { item, rowIndex: idx, colIndex: i };
        });
        carousel.appendChild(card);
      });
      state.rows[idx] = dataset;
      state.rowOffsets[idx] = 0;
    });

    // Elements
    const navItems = Array.from(document.querySelectorAll('.nav-item'));
    const heroBackdrop = document.getElementById('hero-backdrop');
    const heroTitle = document.getElementById('hero-title');
    const heroDesc = document.getElementById('hero-desc');
    const btnPlay = document.getElementById('action-play');
    const btnAdd = document.getElementById('action-add');
    const btnInfo = document.getElementById('action-info');

    // Initialize hero with first item
    setHero(mockItemsA[0]);
    state.selected = { item: mockItemsA[0], rowIndex: 0, colIndex: 0 };

    // Focus initial
    navItems[0]?.focus();

    // Navigation helpers
    function setHero(item) {
      heroBackdrop.src = item.img;
      heroTitle.textContent = item.title;
      heroDesc.textContent = item.desc;
    }

    function focusNav(indexDelta) {
      const current = document.activeElement;
      const idx = navItems.indexOf(current);
      let target = 0;
      if (idx >= 0) {
        target = Math.max(0, Math.min(navItems.length - 1, idx + indexDelta));
      } else {
        target = 0;
      }
      navItems.forEach((n, i) => n.setAttribute('aria-selected', i === target ? 'true' : 'false'));
      navItems[target].focus();
      state.focus.area = 'nav';
    }

    function getRowAndColFromFocus() {
      const el = document.activeElement;
      if (el && el.classList.contains('card')) {
        const r = Number(el.getAttribute('data-row'));
        const c = Number(el.getAttribute('data-col'));
        return { r, c };
      }
      return { r: -1, c: -1 };
    }

    function focusRow(rowDelta) {
      // move between rows from nav or cards
      const { r, c } = getRowAndColFromFocus();
      const targetRow = Math.max(0, Math.min(state.rows.length - 1, (r === -1 ? 0 : r + rowDelta)));
      const rowEl = document.querySelector(`.row[data-rowindex="${targetRow}"] .carousel`);
      if (!rowEl) return;

      const cols = rowEl.children.length;
      const targetCol = Math.max(0, Math.min(cols - 1, c === -1 ? 0 : c));
      const targetCard = rowEl.children[targetCol];
      if (targetCard) {
        targetCard.focus();
        ensureCardVisible(targetRow, targetCol);
        state.focus.area = 'rows';
        state.focus.rowIndex = targetRow;
        state.focus.colIndex = targetCol;
      }
    }

    function focusCol(dx) {
      const { r, c } = getRowAndColFromFocus();
      if (r === -1) return;
      const rowEl = document.querySelector(`.row[data-rowindex="${r}"] .carousel`);
      if (!rowEl) return;
      const cols = rowEl.children.length;
      const target = Math.max(0, Math.min(cols - 1, c + dx));
      const card = rowEl.children[target];
      if (card) {
        card.focus();
        ensureCardVisible(r, target);
        state.focus.rowIndex = r;
        state.focus.colIndex = target;
      }
    }

    function ensureCardVisible(rowIndex, colIndex) {
      const carousel = document.querySelector(`.row[data-rowindex="${rowIndex}"] .carousel`);
      if (!carousel) return;

      const visibleCount = Math.floor((window.innerWidth - 2 * 48) / (320 + 24)); // approx from styles
      const maxIndex = state.rows[rowIndex].length - 1;
      const minStart = 0;
      const maxStart = Math.max(0, maxIndex - (visibleCount - 1));

      // compute desired start index so focused card stays within viewport window
      let start = Math.min(Math.max(colIndex - 1, minStart), maxStart);
      const targetX = -start * (320 + 24);
      state.rowOffsets[rowIndex] = targetX;
      carousel.style.transform = `translateX(${targetX}px)`;
    }

    function focusHeroActions(dy) {
      // Enter hero area and cycle through action buttons with Left/Right
      const actions = [btnPlay, btnAdd, btnInfo];
      const current = document.activeElement;
      let idx = actions.indexOf(current);
      if (idx === -1) {
        idx = 0;
      } else {
        idx = Math.max(0, Math.min(actions.length - 1, idx + dy));
      }
      actions[idx].focus();
      state.focus.area = 'hero';
      state.focus.actionIndex = idx;
    }

    function triggerPlayFeedback() {
      // simple visual feedback by pulsing hero image
      const img = heroBackdrop;
      if (img && img.animate) {
        img.animate(
          [{ transform: 'scale(1.02)' }, { transform: 'scale(1.06)' }, { transform: 'scale(1.02)' }],
          { duration: 260, easing: 'ease-out' }
        );
      }
    }

    // Overlay (Info)
    const overlay = document.getElementById('info-overlay');
    const overlayBackdrop = document.getElementById('overlay-backdrop');
    const overlayTitle = document.getElementById('overlay-title');
    const overlayMeta = document.getElementById('overlay-meta');
    const overlayDesc = document.getElementById('overlay-desc');
    const overlayPlay = document.getElementById('overlay-play');
    const overlayAdd = document.getElementById('overlay-add');
    const overlayClose = document.getElementById('overlay-close');

    function openOverlay() {
      if (!state.selected) return;
      overlayTitle.textContent = state.selected.item.title;
      overlayDesc.textContent = state.selected.item.desc;
      overlay.setAttribute('aria-hidden', 'false');
      state.focus.area = 'overlay';
      // focus first action
      overlayPlay.focus();
    }
    function closeOverlay() {
      overlay.setAttribute('aria-hidden', 'true');
      // return focus to Info button
      btnInfo.focus();
      state.focus.area = 'hero';
    }

    // Button interactions
    btnPlay.addEventListener('click', triggerPlayFeedback);
    btnAdd.addEventListener('click', () => {
      // quick flash to indicate add
      if (btnAdd.animate) {
        btnAdd.animate([{ transform: 'scale(1)' }, { transform: 'scale(0.96)' }, { transform: 'scale(1)' }], { duration: 150 });
      }
    });
    btnInfo.addEventListener('click', openOverlay);
    overlayClose.addEventListener('click', closeOverlay);
    overlayPlay.addEventListener('click', triggerPlayFeedback);
    overlayAdd.addEventListener('click', () => {
      if (overlayAdd.animate) {
        overlayAdd.animate([{ transform: 'scale(1)' }, { transform: 'scale(0.96)' }, { transform: 'scale(1)' }], { duration: 150 });
      }
    });

    // Keyboard / Remote handling
    document.addEventListener('keydown', (e) => {
      const key = e.key;

      // Prevent default browser scrolling
      if (key.startsWith('Arrow')) e.preventDefault();

      if (state.focus.area === 'overlay') {
        switch (key) {
          case 'ArrowRight':
            e.preventDefault();
            cycleOverlay(1); break;
          case 'ArrowLeft':
            e.preventDefault();
            cycleOverlay(-1); break;
          case 'ArrowDown':
          case 'ArrowUp':
            e.preventDefault(); // keep focus inside overlay row
            break;
          case 'Enter':
          case 'NumpadEnter':
            e.preventDefault();
            document.activeElement?.click();
            break;
          case 'Escape':
          case 'Backspace':
            e.preventDefault();
            closeOverlay();
            break;
        }
        return;
      }

      switch (key) {
        case 'ArrowRight': {
          if (document.activeElement?.classList.contains('nav-item')) {
            focusNav(1);
          } else if (document.activeElement === btnPlay || document.activeElement === btnAdd || document.activeElement === btnInfo) {
            focusHeroActions(1);
          } else {
            focusCol(1);
          }
          break;
        }
        case 'ArrowLeft': {
          if (document.activeElement?.classList.contains('nav-item')) {
            focusNav(-1);
          } else if (document.activeElement === btnPlay || document.activeElement === btnAdd || document.activeElement === btnInfo) {
            focusHeroActions(-1);
          } else {
            focusCol(-1);
          }
          break;
        }
        case 'ArrowDown': {
          if (document.activeElement?.classList.contains('nav-item')) {
            focusRow(0); // from nav to first row
          } else if (document.activeElement === btnPlay || document.activeElement === btnAdd || document.activeElement === btnInfo) {
            focusRow(0);
          } else {
            // rows: try next row
            focusRow(1);
          }
          break;
        }
        case 'ArrowUp': {
          if (document.activeElement?.classList.contains('nav-item')) {
            // stay in nav
            focusNav(0);
          } else if (document.activeElement === btnPlay || document.activeElement === btnAdd || document.activeElement === btnInfo) {
            // go to nav
            navItems[0].focus();
            state.focus.area = 'nav';
          } else {
            // from rows, try hero then nav
            btnPlay.focus();
            state.focus.area = 'hero';
          }
          break;
        }
        case 'Enter':
        case 'NumpadEnter': {
          // Primary action is Play
          if (document.activeElement === btnInfo) {
            openOverlay();
          } else {
            triggerPlayFeedback();
          }
          break;
        }
        case 'Escape':
        case 'Backspace': {
          // Close overlay if any (handled above), otherwise subtle reset pulse
          const img = heroBackdrop;
          if (img && img.animate) {
            img.animate([{ opacity: 1 }, { opacity: 0.85 }, { opacity: 1 }], { duration: 180 });
          }
          break;
        }
        default:
          break;
      }
    });

    function cycleOverlay(dx) {
      const focusables = [overlayPlay, overlayAdd, overlayClose];
      const idx = focusables.indexOf(document.activeElement);
      let next = 0;
      if (idx >= 0) next = Math.max(0, Math.min(focusables.length - 1, idx + dx));
      focusables[next].focus();
    }
  }

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initTvHome);
  } else {
    initTvHome();
  }
})();
