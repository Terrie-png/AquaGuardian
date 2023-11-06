import React, { Component } from "react";
import {Link} from "react-router-dom"
import { ACCESS_LEVEL_ADMIN, ACCESS_LEVEL_GUEST } from "../config/global_constants";

export default class Navbar extends Component {
  render() {
    return (
      <div id="navbarTop">
        <div id="navbarBottom">
          <nav>
            <div className="navLinks">
              <ul>
                <li>
                  <div className="logo">
                    <img src="logo.png" alt="Description of the image">
                    </img>
                  </div>

                </li>
                <li>
                  <i className="fa-sharp fa-solid fa-house"></i>
                  <Link className="blue-button" to={"/DisplayAllCars"}>Home</Link>

                </li>
                {
                  localStorage.accessLevel >= ACCESS_LEVEL_ADMIN ? 
                  <li>
                  <i class="fa-sharp fa-solid fa-car"></i>
                  <Link className="blue-button" to={"/AddCarPart"}>Add New Car</Link>
                  </li>
                  :
                  <li>
                  <i class="fa-sharp fa-solid fa-car"></i>
                  <Link disabled={true} className="blue-button" to={"/AddCarPart"}>Add New Car</Link>
                  </li>
                }
                
                {
                  localStorage.accessLevel > ACCESS_LEVEL_GUEST ?
                  <li>
                  <i class="fa-sharp fa-solid fa-cart-shopping"></i>
                  <Link className="blue-button" to={"/ShoppingCart"}>Cart</Link>
                  </li>
                  :
                  <li>
                  <i class="fa-sharp fa-solid fa-cart-shopping"></i>
                  <Link disabled={true} className="blue-button" to={"/ShoppingCart"}>Cart</Link>
                  </li>
                }
                
          
                {
                  localStorage.accessLevel >= ACCESS_LEVEL_ADMIN ?
                  <li>
                  <i class=" fa-sharp fa-solid fa-hammer"></i>
                  <Link className="blue-button" to={"/DisplayAdminTable"}>Admin</Link>
                  </li>
                  :
                  <li>
                  <i class=" fa-sharp fa-solid fa-hammer"></i>
                  <Link disbaled={true} className="blue-button" to={"/DisplayAdminTable"}>Admin</Link>
                  </li>
                }
                <li>

                  <button className="searchbutton" type="submit">Search</button>
                  <input className="searchfrom" type="search" placeholder="Search" aria-label="Search" onChange={this.props.searchArray}></input>

                </li>
                <li>
                
                {
localStorage.accessLevel > ACCESS_LEVEL_GUEST ?
<Link className="login-nav-button" to={"/Login"}><i class=" fa-solid fa-user"></i>Login</Link>
:
<Link className="login-nav-button" to={"/Logout"}><i class=" fa-solid fa-user"></i>Logout</Link>
                }
                
       </li>
              
              </ul>
            </div>
          </nav>
        </div>
      </div>
    )
  }
}