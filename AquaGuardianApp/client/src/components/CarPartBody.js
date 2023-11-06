import React, { Component } from "react"
import CarPartCard from "./CarPartCard"


export default class CarPartBody extends Component {
    constructor(props){
        super(props)
        this.state= {
            isAddable: this.props.isAddable
        }
    }
    render() {
        return (
            <div>
                {this.props.cars.map((part) => <CarPartCard addable={this.state.isAddable} key={part._id} part={part} />)}
            </div>
        )
    }
}