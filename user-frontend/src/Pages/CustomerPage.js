import React, { useContext, useState } from 'react';
import '../Css/accountsettings.css';
import { UserContext } from '../Components/UserContext';
import image from '../Css/img_avatar2.png'
import axios from 'axios';
import { Modal, Button } from 'react-bootstrap';


const CustomerPage = () => {
    const { userData, setUserData } = useContext(UserContext);
   
    // If userData is undefined or null, prevents errors from occurring when trying to access properties of undefined or null objects
    const { userid, userName, password, email, address, phone } = userData || {};



    const [id, setId] = useState(userid);
    const [name, setName] = useState(userName);
    const [email1, setEmail] = useState(email);
    const [password1, setPassword] = useState(password);
    const [phone1, setPhone] = useState(phone);
    const [address1, setAddress] = useState(address);
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
                fullName: name,
                email: email1,
                telephone: phone1,
                password: password1,
                address: address1
            };

            //The updatedDetails object is passed as the request payload
            await axios.put(`http://localhost:8080/users/updateCustomer/${id}`, updatedDetails);

            // Update the userData in the context
        setUserData((prevUserData) => ({
            ...prevUserData,
            userName: name,
            email: email1,
            password: password1,
            phone: phone1,
            address: address1,
        }));
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
                                    value={password1}
                                    onChange={(e) => setPassword(e.target.value)}
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
                                    value={address1}
                                    onChange={(e) => setAddress(e.target.value)}
                                />

                                <button className="saveButton" onClick={handleSave}>Save</button>
                            </>
                        ) : (
                            <>
                                <h1 className="itemTitle">{userName}</h1>
                                <div className="detailItem">
                                    <span className="itemKey">UserId:</span>
                                    <span className="itemValue">{id}</span>
                                </div>
                                <div className="detailItem">
                                    <span className="itemKey">Email:</span>
                                    <span className="itemValue">{email}</span>
                                </div>
                                <div className="detailItem">
                                    <span className="itemKey">Password:</span>
                                    <span className="itemValue">{password}</span>
                                </div>
                                <div className="detailItem">
                                    <span className="itemKey">Phone:</span>
                                    <span className="itemValue">{phone}</span>
                                </div>
                                <div className="detailItem">
                                    <span className="itemKey">Address:</span>
                                    <span className="itemValue">{address}</span>
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

export default CustomerPage;