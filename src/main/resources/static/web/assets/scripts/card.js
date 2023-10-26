const { createApp } = Vue;

createApp({
  data() {
    return {
      clientsInformation: {},
      cards: [],
      creditCard:[],
      debitCard:[],
    };
  },
  created() {
    axios
      .get("/api/clients/2")
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
  methods:{
filterCredit(){
  this.creditCard = this.cards.filter(card => card.type === "CREDIT")
},
filterDebit(){
  this.debitCard = this.cards.filter(card => card.type === "DEBIT")
},
  },
}).mount("#app");
