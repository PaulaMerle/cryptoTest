<template>
  <button>
    <slot></slot>
  </button>
</template>

<style scoped>
button {
  font: inherit;
  border: 1px solid #14005e;
  background-color: #14005e;
  color: white;
  padding: 0.5rem 2rem;
  cursor: pointer;
  border-radius: 10px;
}

button:hover,
button:active {
  background-color: #5c0556;
  border-color: #5c0556;
}
</style>