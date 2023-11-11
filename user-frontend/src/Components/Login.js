import React, { useContext, useState } from "react"
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import { jwtDecode } from "jwt-decode";
import { UserContext } from '../Components/UserContext';
import "../Css/login.css"


function Login() {
   
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const { setUserData } = useContext(UserContext);
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

              
              if (decodedUser.role === 'CUSTOMER') {

                // Fetch the user's name after login
                const userRes = await axios.get(`http://localhost:8080/users/${decodedUser.sub}`);
                console.log(userRes.data);
               
                const userid = userRes.data.id;
                const userName = userRes.data.fullName;
                const phone = userRes.data.telephone;
                const email = userRes.data.email;
                const address = userRes.data.address;
        
        
                setUserData({
                    userid,
                  userName,
                  phone,
                email,
                address
                });

                // Pass user information to the customer page
                navigate('/customer', { state: { user: decodedUser } });
            } else if (decodedUser.role === 'INVENTORY_MANAGER') {

                    // Fetch the user's name after login
                const userRes = await axios.get(`http://localhost:8080/users/${decodedUser.sub}`);
                console.log(userRes.data);
               

                const userid = userRes.data.id;
                const userName = userRes.data.fullName;
                const phone = userRes.data.telephone;
                const email = userRes.data.email;
                
        
        
                setUserData({
                    userid,
                  userName,
                  phone,
                email,
             
                });

                navigate('/inventoryManager', { state: { user: decodedUser } });
            } else if (decodedUser.role === 'DELIVERY_PERSON') {

                    // Fetch the user's name after login
                    const userRes = await axios.get(`http://localhost:8080/users/${decodedUser.sub}`);
                    console.log(userRes.data);
                   
    
                    const userid = userRes.data.id;
                    const userName = userRes.data.fullName;
                    const phone = userRes.data.telephone;
                    const email = userRes.data.email;
                    const areacode = userRes.data.areaCode;
                    
            
            
                    setUserData({
                        userid,
                      userName,
                      phone,
                    email,
                    areacode
                 
                    });


                navigate('/deliveryPerson', { state: { user: decodedUser } });
            } else {
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
                <hr />
            </div>
            <div>
                <div>
                    <form onSubmit={login}>
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
                        </div>
                        <div className="form-group">
                            <label>Password</label>
                            <input
                                type="password"
                                className="form-control"
                                id="password"
                                placeholder="Enter Password"
                                value={password}
                                onChange={(event) => {
                                    setPassword(event.target.value);
                                }}
                            />
                        </div>
                        <button type="submit">Login</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
);
  }
  
  export default Login;