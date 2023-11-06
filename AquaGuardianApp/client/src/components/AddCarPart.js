import React, { Component } from "react"
import { Redirect, Link } from "react-router-dom"
import Form from "react-bootstrap/Form"

import axios from "axios"

import LinkInClass from "../components/LinkInClass"

import Navbar from "./Navbar"
import { SERVER_HOST } from "../config/global_constants"


export default class AddCarPart extends Component {
    constructor(props) {
        super(props)

        this.state = {
            name: "",
            item_number: "",
            material: "",
            colour: "",
            price: "",
            condition: "",
            quantity: "",
            redirectToDisplayAllCars: false
        }
    }


    componentDidMount() {
        this.inputToFocus.focus()
    }


    handleChange = (e) => {
        this.setState({ [e.target.name]: e.target.value })
    }


    handleSubmit = (e) => {
        e.preventDefault()

        const carObject = {
            name: this.state.name,
            photo: "https://img.vast.com/usnews/3707326471654947088/1/320x240",
            item_number: this.state.item_number,
            material: this.state.material,
            colour: this.state.colour,
            price: this.state.price,
            condition: this.state.condition,
            quantity: this.state.quantity
        }

        axios.post(`${SERVER_HOST}/carParts`, carObject)
            .then(res => {
                if (res.data) {

                    console.log(res.data, "here")
                    if (res.data.errorMessage) {
                        console.log(res.data.errorMessage)
                    }
                    else {
                        console.log("Record added")
                        this.setState({ redirectToDisplayAllCars: true })
                    }
                }
                else {
                    console.log("Record not added")
                }
            })
    }


    render() {
        return (
            <div className="navbarBottom">
            <Navbar Navbar={this.state.Navbar} /> 
            
                {this.state.redirectToDisplayAdminTable ? <Redirect to="/DisplayAdminTable" /> : null}
                <div className="addFrom">
                    <Form>
                        <Form.Group controlId="name">
                            <Form.Label>Name</Form.Label>
                            <Form.Control ref={(input) => { this.inputToFocus = input }} type="text" name="name" value={this.state.name} onChange={this.handleChange} />
                        </Form.Group>

                        <Form.Group controlId="item_number">
                            <Form.Label>Item Number</Form.Label>
                            <Form.Control type="text" name="item_number" value={this.state.item_number} onChange={this.handleChange} />
                        </Form.Group>

                        <Form.Group controlId="material">
                            <Form.Label>Material</Form.Label>
                            <Form.Control type="text" name="material" value={this.state.material} onChange={this.handleChange} />
                        </Form.Group>

                        <Form.Group controlId="colour">
                            <Form.Label>Colour</Form.Label>
                            <Form.Control type="text" name="colour" value={this.state.colour} onChange={this.handleChange} />
                        </Form.Group>

                        <Form.Group controlId="price">
                            <Form.Label>Price</Form.Label>
                            <Form.Control type="text" name="price" value={this.state.price} onChange={this.handleChange} />
                        </Form.Group>

                        <Form.Group controlId="condition">
                            <Form.Label>Condition</Form.Label>
                            <Form.Control type="text" name="condition" value={this.state.condition} onChange={this.handleChange} />
                        </Form.Group>

                        <Form.Group controlId="quantity">
                            <Form.Label>Quantity</Form.Label>
                            <Form.Control type="text" name="quantity" value={this.state.quantity} onChange={this.handleChange} />
                        </Form.Group>
                        <LinkInClass value="Add" className="add-button" onClick={this.handleSubmit} />

                    <Link className="red-button" to={"/DisplayCarTable"}>Cancel</Link>
                </Form>
            </div>
        </div>
    
        )
    }
}