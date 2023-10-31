const { createApp } = Vue;

createApp({
  data() {
    return {
      cardType: "",
      cardColor: "",
      messageError:""
    };
  },
  created() {},
  methods: {
    createCard() {
      axios
        .post(
          "/api/clients/current/cards",
          `type=${this.cardType}&color=${this.cardColor}`
        )
        .then((response) => {
          location.pathname = "/web/assets/pages/card.html";
        })
        .catch((error) => 
        this.messageError = error.response.data);
    },
  },
}).mount("#app");
