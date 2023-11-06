
import React, { Component } from "react"
import { Link } from "react-router-dom"

import axios from "axios"
import { SERVER_HOST } from "../config/global_constants"
// import Logout from "./Logout"
import CarPartBody from "./CarPartBody"
import Navbar from "./Navbar"
import Sidenav from "./SideNav"
import '../css/style.css'

export default class DisplayAllCars extends Component {
    constructor(props) {
        super(props)

        this.state = {
            carPartsReset: [],
            carParts: [],
            showModal: false
        }
        this.handleOnClick = this.handleOnClick.bind(this)
        this.sortByPriceHighToLow = this.sortByPriceHighToLow.bind(this)
    }


    componentDidMount() {
        axios.get(`${SERVER_HOST}/carParts`)
            .then(res => {
                if (res.data) {
                    if (res.data.errorMessage) {
                        console.log(res.data.errorMessage)
                    }
                    else {
                        console.log("Records read")
                        this.setState({ carParts: res.data })
                        this.setState({ carPartsReset: res.data })
                        console.log(res.data)
                    }
                }
                else {
                    console.log("Record not found")
                }
            })
    }

    handleOnClick() {
        this.setState({ showModal: !this.state.showModal })
    }

    sortByPriceHighToLow(){
        let sortedDataHL;
        sortedDataHL = this.state.carParts.sort((a,b) => (b.price - a.price))
        this.setState({carParts: sortedDataHL} )
    }

    sortByPriceLowToHigh(){
        let sortedDataLH;
        sortedDataLH = this.state.carParts.sort((a,b) => (a.price - b.price))
        this.setState({carParts: sortedDataLH} )
    }
    filterByNew() {
        console.log(this.state.carParts);
        this.state.carParts = this.state.carPartsReset;
        this.setState({ carParts: this.state.carParts.filter((part) => part.condition.includes("New")) });
    }
    filterByUsed() {
        console.log(this.state.carParts)
        this.state.carParts = this.state.carPartsReset;
        this.setState({ carParts: this.state.carParts.filter((part) => part.condition.includes("Used")) });
    }
    filterByOther() {
        console.log(this.state.carParts)
        this.state.carParts = this.state.carPartsReset;
        this.state.carParts = this.state.carParts.filter((part) => !part.condition.includes("New"));
        this.setState({ carParts: this.state.carParts.filter((part) => !part.condition.includes("Used")) });
    }
    resetArray() {
        this.state.carParts = this.state.carPartsReset;
        this.setState({ carParts: this.state.carPartsReset });
        console.log(this.state.carParts);
    }

    searchArray(e){
        console.log(e.target.value)
            this.state.carParts = this.state.carPartsReset;
            this.setState({carParts:this.state.carParts.filter((part) => part.name.toLowerCase().includes(e.target.value.toLowerCase()))})
    }
    render() {
        return (
            <div className="adminpage">
            <div className="navbarBottom">
                <Navbar Navbar={this.state.Navbar} cars={this.state.carParts} />
                <div className="main-body">
                    {/* <div className="sidenav">
                        <Sidenav Sidenav={this.state.Sidenav}
                            highLow={this.sortByPriceHighToLow.bind(this)}
                            lowHigh={this.sortByPriceLowToHigh.bind(this)}
                            filterNew={this.filterByNew.bind(this)}
                            filterUsed={this.filterByUsed.bind(this)}
                            filterOther={this.filterByOther.bind(this)} />
                        <button onClick={this.resetArray.bind(this)}>Reset Filters</button>
                    </div> */}
                    <div className="table-container">
                        <CarPartBody cars={this.state.carParts} />
                    </div>
                </div>
            </div>
            </div>
        
        )
    }
}