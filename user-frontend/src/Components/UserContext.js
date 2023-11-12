import React, { createContext, useState } from 'react';

//used to share the user data across multiple components in application
export const UserContext = createContext();

export const UserProvider = ({ children }) => {

  //The initial value of userData is set to null
  const [userData, setUserData] = useState(null);

  return (
    // allows the children components to access the user data and the setter function from the UserContext
    <UserContext.Provider value={{ userData, setUserData }}>
      {children}
    </UserContext.Provider>
  );
};