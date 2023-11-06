import React, {Component} from "react"
import { Link } from "react-router-dom"


export default class UserTableRow extends Component 
{    
    render() 
    {
        return (
            <tr>
                <td>{this.props.user.id}</td>
                <td>{this.props.user.name}</td>
                <td>{this.props.part.email}</td>
                <td>{this.props.part.password}</td>
 
                <td><Link className="green-button" to={"/EditCarPart/" + this.props.part._id}>Edit</Link></td>
                <td><Link className="red-button" to={"/DeleteCarPart/" + this.props.part._id}>Delete</Link></td>
            </tr>
        )
    }
}
