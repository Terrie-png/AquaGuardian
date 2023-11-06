import React, { Component } from "react";

export default class Search extends React.Component{
    state ={term: ''};
    onInputChange = (event)=>{
            this.setState({term:event.target.value})
    };
    render(){
        return(
            <div>
                    <div className="search-bar">
                        <form className="search-form">
                            <div className="search">
                             <label>INPUT SEARCH</label>       
                                <input
                                    type="text" value={this.state.term} onChange={this.onInputChange}
                                    />
                            </div>
                        </form>
                    </div>

            </div>

        );
    }
}

