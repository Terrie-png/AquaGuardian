import React, {Component} from "react"
import { Link } from "react-router-dom"


export default class AdminCarPartsRow extends Component 
{    
    render() 
    {
        return (
            <div className="admin table">
            <tr>
                <td>{this.props.part.name}</td>
                <td>{this.props.part.item_number}</td>
                <td>{this.props.part.material}</td>
                <td>{this.props.part.colour}</td>
                <td>{this.props.part.price}</td>
                <td>{this.props.part.condition}</td>
                <td><Link className="green-button" to={"/EditCarPart/" + this.props.part._id}>Edit</Link></td>
                <td><Link className="red-button" to={"/DeleteCarPart/" + this.props.part._id}>Delete</Link></td>
            </tr>
            </div>
        )
    }
}
