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
          alert(error.response.data)
          console.log(error);
        });
    },
  
  getInterestPercentage() {
    return this.loans.find((loan) => loan.id === this.loanId)
      .interestPercentage;
  },
  logoutClient() {
    axios
      .post("/api/logout")
      .then((response) => {
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Logged out successfully",
          showConfirmButton: false,
          timer: 1500,
        }),
          setTimeout(() => {
            location.pathname = `/index.html`;
          }, 1600);
        console.log("signed out!!!");
      })
      .catch((error) => console.log(error));
  },
},
}).mount("#app");
