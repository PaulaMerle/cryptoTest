<template>
  <div>
    <base-button class="addButton" @click="showForm"
      >Lisa uus krüpto oma portfelli</base-button>
    <form v-if="isVisible" @submit.prevent="submitCrypto">
      <p v-if="inputValidation" class="error">Kõik väljad peavad olema täidetud!</p>
      <base-card>
        <base-card class="form-control">
          <label for="currency">Vali krüptovaluuta: </label>
          <select id="currency" name="currency" v-model="crypto.chosenCurrency">
            <option value="Bitcoin">Bitcoin</option>
            <option value="Ethereum">Ethereum</option>
            <option value="Ripple">Ripple</option>
          </select>
        </base-card>
        <base-card class="form-control">
          <label for="amount">Sisesta arv: </label>
          <input
            type="number"
            min="1"
            id="amount"
            name="amount"
            v-model.trim="crypto.enteredAmount"
          />
        </base-card>
        <base-card class="form-control">
          <label for="location">Sisesta hoiukoht: </label>
          <input
            type="text"
            id="location"
            name="location"
            v-model.trim="crypto.enteredLocation"
          />
        </base-card>
        <base-card class="form-control">
          <label for="date">Ostuaeg: </label>
          <input
            type="date"
            id="date"
            name="date"
            v-model.trim="crypto.enteredDate"
          />
        </base-card>
        <base-button>Lisa</base-button>
      </base-card>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      crypto: {
        id: null,
        chosenCurrency: "",
        enteredAmount: null,
        enteredLocation: "",
        enteredDate: "",
      },
      isVisible: false,
    };
  },
  props: ['inputValidation'],
  emits: ["add-crypto"],
  methods: {
    refreshInput() {
      this.crypto.chosenCurrency = "";
      this.crypto.enteredAmount = null;
      this.crypto.enteredLocation = "";
      this.crypto.enteredDate = "";
    },
    submitCrypto() {
      this.$emit(
        "add-crypto",
        this.id,
        this.crypto.chosenCurrency,
        this.crypto.enteredAmount,
        this.crypto.enteredLocation,
        this.crypto.enteredDate
      );
      this.refreshInput();
    },
    showForm() {
      this.isVisible = !this.isVisible;
    },
  },
};
</script>

<style scoped>
.addButton {
  margin-top: 20px;
}

input,
select {
  width: 40%;
  border-radius: 4px;
  padding: 1px 2px;
  border: gray solid 1px;
}

label {
  padding-right: 5%;
}

label,
select,
input {
  font-size: 18px;
}

.form-control {
  background-color: aquamarine;
  width: 30vw;
  display: flex !important;
  justify-content: flex-end;
  padding-right: 10%;
}

.error {
color: red;
font-weight: bold;
}
</style>
