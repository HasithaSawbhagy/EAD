// index.js or App.js
import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import { UserProvider } from './Components/UserContext';
import App from './App';

ReactDOM.render(
    <BrowserRouter>
        <UserProvider>
      <App />
      </UserProvider>
    </BrowserRouter>,
    document.getElementById('root')
);
