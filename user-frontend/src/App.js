import { BrowserRouter,Routes,Route } from "react-router-dom";
import Home from "./Components/home";

import Login from "./Components/Login";
import RegisterCustomer from "./Components/RegisterCustomer";
import RegisterInventoryKeeper from "./Components/RegisterInventoryKeeper";
import RegisterDeliveryPerson from "./Components/RegisterDeliveryPerson";


function App() {
  return (
    <div>
      <BrowserRouter>
            <Routes>
              <Route path="/home" element= { <Home/>} />
            
              <Route path="/" element= { <Login/>} />
              <Route path="/registerCustomer" element= { <RegisterCustomer/>} />
              <Route path="/registerInventoryKeeper" element= { <RegisterInventoryKeeper/>} />
              <Route path="/registerDeliveryPerson" element= { <RegisterDeliveryPerson/>} />
            </Routes>
        </BrowserRouter>
      
    </div>
  );
}
export default App;