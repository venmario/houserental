$("#btnregis").on("click", function () {
  const fullname = $("#fullname").val();
  const username = $("#username").val();
  const notelp = $("#notelp").val();
  const password = $("#password").val();
  const repassword = $("#repassword").val();

  function flasher(status) {
    $("#exampleModal").modal("show");
    $("#exampleModalLabel").html("Information!");
    if (status == "sukses") {
      $(".modal-body").html("Registrasi berhasil");
      resetClass();
      $("#btnclose").addClass("bg-success");
    } else if (status == "gagal") {
      $(".modal-body").html("Registrasi gagal");
      resetClass();
      $("#btnclose").addClass("bg-danger");
    } else {
      $(".modal-body").html("Password tidak cocok!");
      resetClass();
      $("#btnclose").addClass("bg-danger");
    }
  }

  function resetClass() {
    $("#btnclose").removeClass("bg-danger");
    $("#btnclose").removeClass("bg-success");
  }

  $.ajax({
    url: "http://localhost:43556/WebAppReservation/register_process.jsp",
    data: { fullname: fullname, username: username, notelp: notelp, password: password, repassword: repassword },
    method: "post",
    dataType: "json",
    success: function (data) {
      flasher(data.status);
    },
  });
});
