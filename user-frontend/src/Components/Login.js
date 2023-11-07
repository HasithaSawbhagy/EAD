import {  useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import jwt_decode, { jwtDecode } from "jwt-decode";
import "../Css/login.css"

function Login() {
   
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    async function login(event) {
      event.preventDefault();
      try {
          const response = await axios.post("http://localhost:8080/users/login", {
              email: email,
              password: password,
          });
  
          console.log(response.data);
  
          
              // Successful login; you received a token
              const token = response.data;
              
              // Decode the token to get user information
              const decodedUser = jwtDecode(token);

              console.log(decodedUser);
              
              // Now, you can handle different user roles
              if (decodedUser.sub === 'CUSTOMER') {
                  // Redirect to the customer dashboard or show customer-specific content
                  navigate('/register');
              } else if (decodedUser.sub === 'INVENTORY_MANAGER') {
                  // Redirect to the inventory manager dashboard or show inventory manager-specific content
                  navigate('/register');
              } else if (decodedUser.sub === 'DELIVERY_PERSON') {
                  // Redirect to the delivery person dashboard or show delivery person-specific content
                  navigate('/register');
              }
           else {
              // Unsuccessful login; handle the error message
              console.error('Login failed: ' + response.data);
              alert("Login failed. Please check your credentials.");
          }
      } catch (error) {
          // Handle network errors or other issues
          console.error('Error:', error);
          alert("An error occurred while trying to log in.");
      }
  }
      
    return (
      <div>
       
            <div className="container">
            <div>
                <h2>Login</h2>
             <hr/>
             </div>
             <div>
             <div>
 
            <form>
        <div className="form-group">
          <label>Email</label>
          <input type="email"  class="form-control" id="email" placeholder="Enter Email"
          
          value={email}
          onChange={(event) => {
            setEmail(event.target.value);
          }}
          
          />
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password"  class="form-control" id="password" placeholder="Enter Password"
            
            value={password}
            onChange={(event) => {
              setPassword(event.target.value);
            }}
            
            />
          </div>
                  <button type="submit" onClick={login}>Login</button>
              </form>
            </div>
            </div>
            </div>
     </div>
    );
  }
  
  export default Login;