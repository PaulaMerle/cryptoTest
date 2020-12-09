<template>
  <tr>
    <td>{{ name }}</td>
    <td>{{ amount }}</td>
    <td>{{ date }}</td>
    <td>{{ location }}</td>
    <td>{{ value.toFixed(2) }}</td>
    <td><base-button @click="deleteTheCrypto">Delete</base-button></td>
  </tr>
</template>

<script>
import CryptoDataService from "../services/CryptoDataService";

export default {
  data() {
    return {
      currentCrypto: null,
    };
  },
  props: ["name", "amount", "date", "location", "value"],
  emits: ["delete-crypto"],
  methods: {
    /* eslint-disable */
    getCrypto(id) {
      CryptoDataService.get(id)
        .then((response) => {
          this.currentCrypto = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    deleteTheCrypto() {
      this.$emit("delete-crypto");
    },
  },
};
</script>
