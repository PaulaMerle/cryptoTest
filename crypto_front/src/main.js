import { createApp } from 'vue';

import App from './App.vue'
import BaseButton from './components/UI/BaseButton.vue';
import BaseCard from './components/UI/BaseCard.vue';
import CryptoPortfolio from './components/CryptoPortfolio.vue'

const app = createApp(App);

app.component('base-button', BaseButton);
app.component('base-card', BaseCard);
app.component('crypto-portfolio', CryptoPortfolio);

app.mount('#app');
