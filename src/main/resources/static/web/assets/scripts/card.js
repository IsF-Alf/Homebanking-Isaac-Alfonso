const { createApp } = Vue;

createApp({
  data() {
    return {
      clientsInformation: {},
      cards: [],
      creditCard: [],
      debitCard: [],
      currentDate:new Date(),
    };
  },
  created() {
    axios
      .get("/api/clients/current")
      .then((response) => {
        client = response.data;
        this.clientsInformation = client;
        console.log(client);
        this.cards = client.cards;
        console.log(this.cards);
        this.filterCredit();
        console.log(this.creditCard);
        this.filterDebit();
        console.log(this.debitCard);
      })
      .catch((error) => console.log(error));
  },
  methods: {
    deleteCard(id) {
      axios
        .patch(`/api/clients/current/cards`, `id=${id}`)
        .then(() => {
          location.pathname = `/web/assets/pages/card.html`;
        })
        .catch((error) => console.log(error));
    },
    filterCredit() {
      this.creditCard = this.cards.filter((card) => card.type === "CREDIT");
    },
    filterDebit() {
      this.debitCard = this.cards.filter((card) => card.type === "DEBIT");
    },
    logoutClient() {
      axios
        .post("/api/logout")
        .then((response) => {
          console.log("signed out!!!");
          location.pathname = `/index.html`;
        })
        .catch((error) => console.log(error));
    },
  },
}).mount("#app");
