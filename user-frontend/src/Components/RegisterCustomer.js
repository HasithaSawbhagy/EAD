import { useState } from "react";
import axios from "axios";
import "../Css/register.css";

function RegisterCustomer() {
  const [email, setEmail] = useState("");
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [telephone, setTelephone] = useState("");
  const [address, setAddress] = useState("");
  const [errors, setErrors] = useState({});

  const validateEmail = (email) => {
    // Email validation regex
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const validateTelephone = (telephone) => {
    // Telephone validation regex (10 digits only)
    const telephoneRegex = /^\d{10}$/;
    return telephoneRegex.test(telephone);
  };

  const validateForm = () => {
    const errors = {};

    if (!username.trim()) {
      errors.username = "Username is required";
    }

    if (!email.trim()) {
      errors.email = "Email is required";
    } else if (!validateEmail(email)) {
      errors.email = "Invalid email format";
    }

    if (!password.trim()) {
      errors.password = "Password is required";
    }

    if (!role.trim()) {
      errors.role = "Role is required";
    }

    if (!telephone.trim()) {
      errors.telephone = "Telephone is required";
    } else if (!validateTelephone(telephone)) {
      errors.telephone = "Invalid telephone number (10 digits required)";
    }

    if (!address.trim()) {
      errors.address = "Address is required";
    }

    setErrors(errors);
    return Object.keys(errors).length === 0; // Return true if there are no errors
  };

  async function save(event) {
    event.preventDefault();

    if (validateForm()) {
      try {
        await axios.post("http://localhost:8080/users/register", {
          email: email,
          username: username,
          password: password,
          role: role,
          telephone: telephone,
          address: address,
        });
        alert("User Registration Successfully");
      } catch (err) {
        alert(err);
      }
    }
  }

  return (
    <div>
      <div className="container mt-4">
        <div className="card">
          <h1>User Registration</h1>

          <form>
            <div className="form-group">
              <label>User Name</label>
              <input
                type="text"
                className="form-control"
                id="username"
                placeholder="Enter Name"
                value={username}
                onChange={(event) => {
                  setUserName(event.target.value);
                }}
              />
              {errors.username && <p className="error">{errors.username}</p>}
            </div>

            <div className="form-group">
              <label>Email</label>
              <input
                type="email"
                className="form-control"
                id="email"
                placeholder="Enter Email"
                value={email}
                onChange={(event) => {
                  setEmail(event.target.value);
                }}
              />
              {errors.email && <p className="error">{errors.email}</p>}
            </div>

            <div className="form-group">
              <label>Password</label>
              <input
                type="password"
                className="form-control"
                id="password"
                placeholder="Enter password"
                value={password}
                onChange={(event) => {
                  setPassword(event.target.value);
                }}
              />
              {errors.password && <p className="error">{errors.password}</p>}
            </div>

            <div className="form-group">
              <label>Role</label>
              <input
                type="text"
                className="form-control"
                id="role"
                placeholder="Enter User Role"
                value={role}
                onChange={(event) => {
                  setRole(event.target.value);
                }}
              />
              {errors.role && <p className="error">{errors.role}</p>}
            </div>

            <div className="form-group">
              <label>Telephone</label>
              <input
                type="text"
                className="form-control"
                id="telephone"
                placeholder="Enter Telephone"
                value={telephone}
                onChange={(event) => {
                  setTelephone(event.target.value);
                }}
              />
              {errors.telephone && <p className="error">{errors.telephone}</p>}
            </div>

            <div className="form-group">
              <label>Address</label>
              <input
                type="text"
                className="form-control"
                id="address"
                placeholder="Enter address"
                value={address}
                onChange={(event) => {
                  setAddress(event.target.value);
                }}
              />
              {errors.address && <p className="error">{errors.address}</p>}
            </div>

            <button type="submit" className="btn btn-primary mt-4" onClick={save}>
              Save
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default RegisterCustomer;
