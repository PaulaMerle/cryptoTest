/* eslint-disable */

import { ref } from "vue";
import CryptoDataService from "./CryptoDataService.js";

export default function loadPortfolio() {
  const isLoading = ref(false);
  const error = null;
  const results = ref([]);
  const invalidInput = ref(false);
  const submitted = ref(false);
  const id = ref(null);

  const load = async () => {
    isLoading.value = true;
    CryptoDataService.getAll()
      .then((response) => {
        isLoading.value = false;
        results.value = response.data;
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
        isLoading.value = false;
        error.value = "Viga andmete laadimisel, proovi hiljem uuesti.";
      });
  }

  const newCrypto = async (id, currency, amount, location, date) => {
    if (
      currency === "" ||
      amount === null ||
      location === "" ||
      date === ""
    ) {
      invalidInput.value = true;
      return;
    }
    let data = {
      id: null,
      amount: amount,
      currency: currency,
      date: date,
      location: location,
    };
    CryptoDataService.create(data)
      .then((response) => {
        id = response.data.id;
        console.log(response.data);
        submitted.value = true;
        invalidInput.value = false;
        load();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  return { isLoading, error, results, invalidInput, submitted, id, load, newCrypto }
}

