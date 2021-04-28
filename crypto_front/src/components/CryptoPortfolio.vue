<template>
  <div>
    <p v-if="dataLoading">Laeb andmeid...</p>
    <p v-else-if="!dataLoading && errorMessage">{{ errorMessage }}</p>
    <table>
      <thead>
        <tr>
          <th>Krüpto</th>
          <th>Kogus</th>
          <th>Ostu kuupäev</th>
          <th>Hoiukoht</th>
          <th>Turuväärtus (EUR)</th>
          <th>Tegevus</th>
        </tr>
      </thead>
      <portfolio-results
        v-for="crypto in portfolioItems"
        :key="crypto.id"
        :name="crypto.currency"
        :amount="crypto.amount"
        :date="crypto.date"
        :location="crypto.location"
        :value="crypto.value"
        @delete-crypto="deleteCrypto(crypto.id)"
      ></portfolio-results>
    </table>
  </div>
</template>

<script>
import PortfolioResults from "./PortfolioResults.vue";
import CryptoDataService from "../services/CryptoDataService";

export default {
  components: {
    PortfolioResults,
  },
  props: ["portfolioItems", "dataLoading", "errorMessage"],
  emits: ["update-data"],
  setup(props, context) {
    /* eslint-disable */
    function deleteCrypto(id) {
      if (confirm("Kindel, et soovid kustutada?"))
        CryptoDataService.delete(id)
          .then((response) => {
            console.log(response.data);
            context.emit("update-data");
          })
          .catch((e) => {
            console.log(e);
          });
    }
    return { deleteCrypto };
  },
};
</script>

<style scoped>
table {
  margin: 0 auto;
  width: 80%;
  border-collapse: collapse;
  margin-top: 20px;
}

table,
th,
td {
  border: solid lightgray 1px;
  padding: 5px;
}

tr:nth-child(even) {
  background-color: aquamarine;
}

th {
  background-color: #14005e;
  font-size: 1.5vw;
  color: whitesmoke;
}

tr:hover {
  background-color: lavender;
}
button {
  margin: 2em 0 !important;
}
</style>
