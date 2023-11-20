const app = Vue.createApp({
  data() {
    return {
      accounts: {},
      amount: null,
      description: "",
      originNumber: "",
      destinationNumber: "",
    };
  },
  created() {
    axios
      .get("/api/clients/current")
      .then((response) => {
        this.accounts = response.data.accounts;
        console.log(this.accounts);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    generateTransfer() {
      axios
        .post(
          `/api/clients/current/transfers`,
          `amount=${this.amount}&description=${this.description}&originNumber=${this.originNumber}&destinationNumber=${this.destinationNumber}`
        )
        .then(() => {
          location.pathname = `/web/assets/pages/transfers.html`;
        })
        .catch((error) => console.log(error));
    },
  },
  logoutClient() {
    axios
      .post(`/api/logout`)
      .then((response) => {
        console.log("SingOut");
        location.pathname = `/index.html`;
      })
      .catch((error) => {
        console.log(error);
      });
  },
});
app.mount("#app");
