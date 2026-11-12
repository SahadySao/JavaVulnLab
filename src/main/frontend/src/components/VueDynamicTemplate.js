import { createApp } from 'vue';

// Gabarit construit avec le titre reçu : Vue compile et exécute les expressions qu'il contient
export function mountTitle(el, title) {
  createApp({
    template: `<h1>${title}</h1>`,
  }).mount(el);
}
