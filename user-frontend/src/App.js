import { BrowserRouter,Routes,Route } from "react-router-dom";

import Login from "./Components/Login";
import RegisterCustomer from "./Components/RegisterCustomer";
import RegisterInventoryKeeper from "./Components/RegisterInventoryKeeper";
import RegisterDeliveryPerson from "./Components/RegisterDeliveryPerson";
import InventoryManagerPage from "./Pages/InventoryManagerPage";
import DeliveryPersonPage from "./Pages/DeliveryPersonPage";
import CustomerPage from "./Pages/CustomerPage";


function App() {
  return (
    <div>
    
            <Routes>
              <Route path="/" element= { <Login/>} />
              <Route path="/registerCustomer" element= { <RegisterCustomer/>} />
              <Route path="/registerInventoryKeeper" element= { <RegisterInventoryKeeper/>} />
              <Route path="/registerDeliveryPerson" element= { <RegisterDeliveryPerson/>} />
              <Route path="/customer" element= { <CustomerPage/>} />
              <Route path="/deliveryPerson" element= { <DeliveryPersonPage/>} />
              <Route path="/inventoryManager" element= { <InventoryManagerPage/>} />
            </Routes>
        
      
    </div>
  );
}
export default App;