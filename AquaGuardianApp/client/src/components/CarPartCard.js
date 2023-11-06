import React, { Component } from "react"
import { Link } from "react-router-dom"
import { ACCESS_LEVEL_GUEST } from "../config/global_constants"

export default class CarPartCard extends Component {
    constructor(props) {
        super(props)

        this.state = {
            addable: this.props.addable
        }
    }

    addToCart = e => {
        let tempList = []
        let added = false
        if (localStorage.getItem('cart') !== "") {
            tempList.push.apply(tempList, JSON.parse(localStorage.getItem('cart')))
        }

        tempList.forEach(item => {
            if (item.itemId === this.props.part._id) {
                item.quantity++
                added = true
            }
        })
        if (!added) {
            const toAdd = {
                itemId: this.props.part._id,
                itemPrice: this.props.part.price,
                quantity: 1
            }
            tempList.push(toAdd)
        }
        localStorage.setItem('cart', JSON.stringify(tempList))
    }
    render() {
        return (

            <div className="card">
              
                <div className="card_information">
            
                     
                <img className="img" src=
                    {this.props.part.photo} alt="img"/>

    
                <div className="card_title">{this.props.part.name}</div>
                <p><div className="card_price">{this.props.part.price}</div></p>
                <p>Item Number:{this.props.part.item_number}</p>
                
                <p>Material:{this.props.part.material}</p>
                <p>Colour:{this.props.part.colour}</p>
                
                <p>Condition:{this.props.part.condition}</p>
                </div>

                <div className="card_bottom">
                    {localStorage.accessLevl > ACCESS_LEVEL_GUEST ? <button className="add-to-cart-button" onClick={this.addToCart}>Add to Cart</button> : <button className="add-to-cart-button"><Link to={"/Login"}>Add to Cart</Link></button>}
                </div>
            </div>



        )
    }
}