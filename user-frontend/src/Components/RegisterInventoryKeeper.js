import { useState } from "react";
import axios from "axios";
import "../Css/register.css";

function RegisterInventoryKeeper() {
  const [email, setEmail] = useState("");
  const [fullName, setFullName] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");
  const [telephone, setTelephone] = useState("");
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

    if (!fullName.trim()) {
      errors.fullName = "Username is required";
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

    setErrors(errors);
    return Object.keys(errors).length === 0; // Return true if there are no errors
  };

  async function save(event) {
    event.preventDefault();

    if (validateForm()) {
      try {
        await axios.post("http://localhost:8080/users/register_InventoryKeeper", {
          email: email,
          fullName: fullName,
          password: password,
          role: role,
          telephone: telephone,
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
                id="fullName"
                placeholder="Enter Name"
                value={fullName}
                onChange={(event) => {
                  setFullName(event.target.value);
                }}
              />
              {errors.fullName && <p className="error">{errors.fullName}</p>}
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

            <button type="submit" className="btn btn-primary mt-4" onClick={save}>
              Save
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default RegisterInventoryKeeper;
