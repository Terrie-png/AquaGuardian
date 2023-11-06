import React, { Component } from "react"
import UserTableRow from "./UserTableRow"


import axios from "axios"
import { SERVER_HOST } from "../config/global_constants"
export default class UserTable extends Component {
    constructor(props) {
        super(props)

        this.state = {
            user: []
        }
    }


    componentDidMount() {
        axios.get(`${SERVER_HOST}/users`)
            .then(res => {
                if (res.data) {
                    if (res.data.errorMessage) {
                        console.log(res.data.errorMessage)
                    }
                    else {
                        console.log("Records read")
                        this.setState({ user: res.data })
                        console.log(res.data)
                    }
                }
                else {
                    console.log("Record not found")
                }
            })
    }
    render() {
        return (
            <div>
                {this.props.user.map((user) => <UserTableRow key={user.id} user={user} />)}
            </div>
        )
    }
}