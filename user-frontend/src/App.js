import { BrowserRouter,Routes,Route } from "react-router-dom";
import Home from "./Components/home";
import Register from "./Components/Register";
import Login from "./Components/Login";


function App() {
  return (
    <div>
      <BrowserRouter>
            <Routes>
              <Route path="/home" element= { <Home/>} />
              <Route path="/register" element= { <Register/>} />
              <Route path="/" element= { <Login/>} />
            </Routes>
        </BrowserRouter>
      
    </div>
  );
}
export default App;