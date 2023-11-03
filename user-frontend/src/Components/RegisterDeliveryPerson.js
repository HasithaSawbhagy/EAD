import { useState } from "react";
import axios from "axios";
import "../Css/register.css";

function RegisterDeliveryPerson() {

  const [email, setEmail] = useState("");
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [telephone, setTelephone] = useState("");
  const [areacode, setAreacode] = useState("");

  async function save(event) {
    event.preventDefault();
    try {
      await axios.post("http://localhost:8080/users/register_DelveryPerson", {
        email: email,
        username: username,
        password: password,
        role: role,
        telephone: telephone,
        areacode: areacode,
      });
      alert("User Registation Successfully");

    } catch (err) {
      alert(err);
    }
  }

  return (
    <div>
      <div class="container mt-4" >
        <div class="card">
          <h1>User Registation</h1>

          <form>
            <div class="form-group">
              <label>User Name</label>
              <input type="text" class="form-control" id="username" placeholder="Enter Name"

                value={username}
                onChange={(event) => {
                  setUserName(event.target.value);
                }}
              />

            </div>

            <div class="form-group">
              <label>Email</label>
              <input type="email" class="form-control" id="email" placeholder="Enter Email"

                value={email}
                onChange={(event) => {
                  setEmail(event.target.value);
                }}

              />

            </div>

            <div class="form-group">
              <label>Password</label>
              <input type="password" class="form-control" id="password" placeholder="Enter password"

                value={password}
                onChange={(event) => {
                  setPassword(event.target.value);
                }}

              />
            </div>

            <div class="form-group">
              <label>Role</label>
              <input type="role" class="form-control" id="role" placeholder="Enter User Role"

                value={role}
                onChange={(event) => {
                  setRole(event.target.value);
                }}

              />

            </div>

            <div class="form-group">
              <label>Telephone</label>
              <input type="telephone" class="form-control" id="telephone" placeholder="Enter Telephone"

                value={telephone}
                onChange={(event) => {
                  setTelephone(event.target.value);
                }}

              />

            </div>

            <div class="form-group">
              <label>Area Code</label>
              <input type="areacode" class="form-control" id="areacode" placeholder="Enter area code"

                value={areacode}
                onChange={(event) => {
                  setAreacode(event.target.value);
                }}

              />

            </div>

            <button type="submit" class="btn btn-primary mt-4" onClick={save} >Save</button>

          </form>
        </div>
      </div>
    </div>
  );
}

export default RegisterDeliveryPerson;