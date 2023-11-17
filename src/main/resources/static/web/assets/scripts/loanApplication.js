const { createApp } = Vue;

createApp({
  data() {
    return {
      loans: [],
      loanId: 0,
      amount: null,
      payments: 0,
      destinationAccount: "",
      accounts: {},
    };
  },
  created() {
    axios
      .get("/api/loans")
      .then((response) => {
        this.loans = response.data;
        console.log(this.loans);
      })
      .catch((error) => console.log(error));

    axios
      .get("/api/clients/current/accounts")
      .then((response) => {
        this.accounts = response.data;
        console.log(this.accounts);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    applyForLoan() {
      axios
        .post("/api/loans", {
          idLoan: `${this.loanId}`,
          amount: `${this.amount}`,
          payments: `${this.payments}`,
          destinationAccount: `${this.destinationAccount}`,
        })
        .then((result) => {
            location.pathname = `web/accounts.html`;
        })
        .catch((error) => {
          console.log(error);
        });
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
}).mount("#app");
