const { createApp } = Vue;

createApp({
  data() {
    return {
      loans: [],
      loanName: "",
      maxAmount: 0,
      payments: [],
      interestPercentage: 0,
    };
  },
  created() {
    axios
      .get("/api/clients/current")
      .then((response) => {
        client = response.data;
        console.log(client);
      })
      .catch((err) => console.log(err));
  },
  methods: {
    createNewLoan() {
      Swal.fire({
        title: "Are you sure?",
        text: "Are you sure you want to create this loan?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, create this loan!",
      }).then((result) => {
        if (result.isConfirmed) {
          const newLoan = `name=${this.loanName}&maxAmount=${this.maxAmount}&payments=${this.payments}&interestPercentage=${this.interestPercentage}`;
          axios
            .post("/api/admin/loans", newLoan)
            .then((result) => {
              Swal.fire({
                title: "Created!",
                text: "The loan has been created successfully",
                icon: "success",
              });
              setTimeout(() => {
                location.pathname = `/web/accounts.html`;
              }, 1600);
            })
            .catch((error) => {
              console.log(error);
            });
        }
      });
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
    createAccount() {
      axios
        .post(
          `/api/clients/current/accounts`,
          `accountType=${this.accountType}`
        )
        .then((response) => {
          console.log("created");
          location.reload();
        })
        .catch((error) => console.log("error"));
    },
    deleteAccount(id) {
      axios
        .patch(`/api/clients/current/accounts`, `id=${id}`)
        .then(() => {
          location.pathname = `/web/accounts.html`;
        })
        .catch((error) => {
          alert(error.response.data);
        });
    },
  },
}).mount("#app");
