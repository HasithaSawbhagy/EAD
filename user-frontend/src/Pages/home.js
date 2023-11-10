import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../Css/home.css';

function Home() {
  const navigate = useNavigate();
  const [showButtons, setShowButtons] = useState(true);

  const handleLoginClick = () => {
    // Navigate to the login page
    navigate('/login');
  };

  const handleRegisterClick = () => {
    // Hide buttons and show user role selection form
    setShowButtons(false);
  };

  const handleUserRoleSelection = (selectedRole) => {
    // Navigate to the relevant registration page based on the selected role
    if (selectedRole) {
      navigate(`/register/${selectedRole.toLowerCase()}`);
    }
  };

  return (
    <div className="home-container">
      
      {showButtons && (
        
        <div>
          <h1>Home</h1>
          <button className="action-button1" onClick={handleLoginClick}>
            Login
          </button>
          <button className="action-button1" onClick={handleRegisterClick}>
            Register
          </button>
        </div>
      )}

      {!showButtons && (
        <div className="role-container" >
          <p>Select user role:</p>
          <button className="action-button" onClick={() => handleUserRoleSelection('CUSTOMER')}>Customer</button>
          <button className="action-button" onClick={() => handleUserRoleSelection('INVENTORY_MANAGER')}>Inventory Manager</button>
          <button className="action-button" onClick={() => handleUserRoleSelection('DELIVERY_PERSON')}>Delivery Person</button>
        </div>
      )}
    </div>
  );
}

export default Home;
