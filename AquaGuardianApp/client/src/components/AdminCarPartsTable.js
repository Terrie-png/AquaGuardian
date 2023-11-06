import React, { Component } from "react"
import AdminCarPartsRow from "./AdminCarPartsRow"
import {Link} from "react-router-dom"

export default class AdminCarPartsTable extends Component {
    render() {
        return (
            <div className="admin_site">
                <div>
                    <th>Name</th>
                    <th>Item Number</th>
                    <th>Material</th>
                    <th>Colour</th>
                    <th>Price</th>
                    <th>Condition</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </div>
                <div className="admin_table">
                    {this.props.parts.map((part) => <AdminCarPartsRow key={part._id} part={part} />)}
                </div>
            </div>

        )
    }
}