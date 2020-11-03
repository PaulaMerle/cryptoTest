<template>
  <div>
    <the-header></the-header>
    <add-crypto @add-crypto="newCrypto" :input-validation="invalidInput"></add-crypto>
    <crypto-portfolio
      :portfolio-items="results"
      :data-loading="isLoading"
      :error-message="error"
      @update-data="load"
    ></crypto-portfolio>
  </div>
</template>

<script>
import { ref } from "vue";
import { onMounted } from "vue";
import TheHeader from "./components/TheHeader.vue";
import AddCrypto from "./components/AddCrypto.vue";
import CryptoPortfolio from "./components/CryptoPortfolio.vue";
import loadPortfolio from "./services/PortfolioService.js";

export default {
  components: {
    TheHeader,
    AddCrypto,
    CryptoPortfolio,
  },
  setup() {
    const { isLoading, error, results, invalidInput, submitted, id, load, newCrypto } = loadPortfolio();
    const isVisible = ref(false);

    onMounted(() => {
      load();
    });

    return {
      isLoading, error, results, invalidInput, submitted, id, load, newCrypto, isVisible
    };
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
