import React, { Component } from "react";

export default class Sidenav extends Component {
  render() {
    return (
      <div id="sidenav">
        
        
          <nav>
            <div className="filterButtons">
              <ul>
                <li>
               
                
                <button onClick={this.props.filterNew}><i class="fa-sharp fa-solid fa-spray-can-sparkles"></i> Filter By New</button>
                </li>
                <li>
                <button onClick={this.props.filterUsed}><i class="fa-sharp fa-solid fa-stroopwafel"></i> Filter By Used</button>
                </li>
                <li>
                <button onClick={this.props.highLow}><i class="fa-sharp fa-solid fa-euro-sign"></i><i class="fa-sharp fa-solid fa-euro-sign"></i><i class="fa-sharp fa-solid fa-euro-sign"></i> Price: High to Low</button>
                </li>
                <li>
                <button onClick={this.props.lowHigh}><i class="fa-sharp fa-solid fa-euro-sign"></i> Price: Low to High</button>
                </li>
                <li>
                <button onClick={this.props.filterOther}><i class="fa-sharp fa-solid fa-car-battery"></i> Filter By Other</button>
                </li>
              </ul>
            </div>
          </nav>

        </div>

    );
  }
}
