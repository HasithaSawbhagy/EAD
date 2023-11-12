
import TravelExploreIcon from '@mui/icons-material/TravelExplore';
import NotificationsNoneOutlinedIcon from "@mui/icons-material/NotificationsNoneOutlined";
import SettingsIcon from "@mui/icons-material/Settings";
import AccountCircleOutlinedIcon from "@mui/icons-material/AccountCircleOutlined";
import ExitToAppOutlinedIcon from "@mui/icons-material/ExitToAppOutlined";
import DarkModeIcon from '@mui/icons-material/DarkMode';
import LightModeIcon from '@mui/icons-material/LightMode';
//links to different pages
import { Link, NavLink } from "react-router-dom";

import {  useState } from "react";
import { FaCaretDown } from "react-icons/fa";


const Sidebar = () => {

  const [isDropdownOpen, setIsDropdownOpen] = useState(false);

  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };



  const closeDropdown = (e) => {
    if (!e.target.matches(".theme-span")) {
      setIsDropdownOpen(false);
    }
  };

  window.addEventListener("click", closeDropdown);


  return (
    <div className="sidebar">
      <div className="top">
        
          <div className="icon">
            <img  className="icontop" alt="Logo" />
          </div>
          <div className="logo">Welcome !</div>
          <div className="name"></div>
       
      </div>
      <hr />

      <div className="center">
        <ul>
          <li>
            
              <TravelExploreIcon className="icon" />
              <span>Search Bus</span>
        
          </li>
          <li>
           
              <NotificationsNoneOutlinedIcon className="icon" />
              <span>Notifications</span>
            
          </li>
          <li>
            
              <AccountCircleOutlinedIcon className="icon" />
              <span>Profile</span>
          
          </li>
          <li>
            
              <ExitToAppOutlinedIcon className="icon" />
              <span>Logout</span>
         
          </li>
          <li>
            <div className="dropdown">
              
                
                  <DarkModeIcon className="icon" />
               
                  <LightModeIcon className="icon" />
             
                <span className="theme-span">Theme</span>
            
              
            </div>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Sidebar;