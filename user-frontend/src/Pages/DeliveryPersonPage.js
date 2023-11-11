import React, { useContext, useState } from 'react';
import '../Css/accountsettings.css';
import { UserContext } from '../Components/UserContext';
import image from '../Css/img_avatar2.png'
import axios from 'axios';
import { Modal, Button } from 'react-bootstrap';


const DeliveryPersonPage = () => {
  const { userData } = useContext(UserContext);
  // If userData is undefined or null, prevents errors from occurring when trying to access properties of undefined or null objects
  const { userid,userName,  email,phone ,areacode} = userData || {};
 
  

  const [id, setId] = useState(userid);
  const [name, setName] = useState(userName);
  const [email1, setEmail] = useState(email);
  const [phone1, setPhone] = useState(phone);
  const [areaCode, setAreaCode] = useState(areacode);

  const [isEditing, setIsEditing] = useState(false);
  const [isSuccess, setIsSuccess] = useState(false);

  const closeModal = () => {
    setIsSuccess(false);
    setIsEditing(false);//indicating that the user has finished editing the profile
  };

  const handleAlertModalClose = () => {
    setIsSuccess(false);
  
  };

  //updating the user details by making a PUT request to the backend API endpoint
  const handleSave = async () => {
    setIsSuccess(true);

    try {
      const updatedDetails = {
        email: email1,
        phone: phone1,
      };

      //The updatedDetails object is passed as the request payload
      await axios.put(`http://localhost:8080/users/updateCustomer/${email}`, updatedDetails);
      setIsSuccess(true);

    } catch (error) {
      console.error(error);
    }
  };




   

   
  


  return (
    <div className="accountsettings">
      <div className="top">
        <div className="left">
          {!isEditing && (
            <div className="editButton">
              {/* enabling the editing mode */}
              <button onClick={() => setIsEditing(true)}>Edit Profile</button>
            </div>
          )}
          <h1 className="title"></h1>
          <div className="item">
            <img
              src={image}
              alt=""
              className="itemImg"
              
              
            />
          </div>
          <div className="details" >
            {isEditing ? (
              <>
                <input
                  className="editInput"
                  type="text"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                />
                <input
                  className="editInput"
                  type="email"
                  value={email1}
                  onChange={(e) => setEmail(e.target.value)}
                />
                <input
                  className="editInput"
                  type="text"
                  value={phone1}
                  onChange={(e) => setPhone(e.target.value)}
                />
                <input
                  className="editInput"
                  type="text"
                  value={areaCode}
                  onChange={(e) => setAreaCode(e.target.value)}
                />
                
                <button className="saveButton" onClick={handleSave}>Save</button>
              </>
            ) : (
              <>
                <h1 className="itemTitle">{userName}</h1>
                <div className="detailItem">
                  <span className="itemKey">UserID:</span>
                  <span className="itemValue">{id}</span>
                </div>
                <div className="detailItem">
                  <span className="itemKey">Email:</span>
                  <span className="itemValue">{email}</span>
                </div>
                <div className="detailItem">
                  <span className="itemKey">Phone:</span>
                  <span className="itemValue">{phone}</span>
                </div>
                <div className="detailItem">
                  <span className="itemKey">AreaCode:</span>
                  <span className="itemValue">{areaCode}</span>
                </div>
              </>
            )}
          </div>
        </div>
        <Modal
          show={isSuccess}
          onHide={() => setIsSuccess(false)}
          centered
        >
          <Modal.Header closeButton>
            <Modal.Title>Success</Modal.Title>
          </Modal.Header>
          <Modal.Body className="text-center">
            <p>Profile Updated Successfully!</p>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="dark" onClick={handleAlertModalClose}>
              Cancel
            </Button>
            <Button variant="success" onClick={() => closeModal()}>
              OK
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    </div>
  );
            }

export default DeliveryPersonPage;