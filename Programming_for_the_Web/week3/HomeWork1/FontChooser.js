//old notation for .bind(this) is used to conform to tests

class FontChooser extends React.Component {

    constructor(props) {
	super(props);
		this.state = {
			hidden: true,
			checked: props.bold === "true",
			fontSize: parseInt(props.size) > 0 ? parseInt(props.size) : 1
		}
    }

    componentDidMount = () => {

    }
    
    onCheckAction = (e) => {
    	this.setState({checked : !this.state.checked},()=>{
    		// console.log("checkbox status: ", this.state.checkbox)
    	})
    }

    changeHidden = (e) => {
    	if(this.state.hidden){
    		this.setState({hidden: false},() => {
    			// console.log("change: ", this.state.hidden);
    		});
    	}
    	// else{
    	// 	// this.setState({hidden: true})
    	// }
    }

    increase = (e) => {
    	// console.log("press plus")
    	this.setState(function(prevState){
    		return{
    			fontSize: prevState.fontSize + 1
    		}
    	})
    }

    decrease = (e) => {
    	// console.log("press minus")
    	this.setState(function(prevState){
   			if(prevState.fontSize > 1){
   				return{
    				fontSize: prevState.fontSize - 1,
    			}
   			}
    	})
    }

    reset = (e) => {
    	this.setState({
    		fontSize: parseInt(this.props.size),
    		hidden: true,
    		checked: this.props.bold === "true"
    	});
    }

    render() {
    	console.log(this.state)
    	// var fontColor = "red"
    	var fontColor = (this.state.fontSize <= parseInt(this.props.min) || this.state.fontSize >= parseInt(this.props.max)) ? 'red' : 'black';

		return(
	       <div 
	       		style={{ flex: 1, paddingTop: 50, paddingBottom: 50 }}
	       		// onMouseOut={this.changeHidden.bind(this)}
	       >
	       <input 
	       		type="checkbox" 
	       		id="boldCheckbox"
	       		checked={this.state.checked} 
	       		hidden={this.state.hidden}
	       		onChange={this.onCheckAction.bind(this)} 
	       	/>
	       <button 
	       		id="decreaseButton" 
	       		hidden={this.state.hidden}
	       		onClick={this.decrease.bind(this)}
	       		disabled={this.state.fontSize <= this.props.min}
	       	>-</button>
	       <span 
	       		id="fontSizeSpan" 
	       		hidden={this.state.hidden}
	       		style={{
	       			color: fontColor
	       		}}
	       		onDoubleClick={this.reset.bind(this)}
	       	>{this.state.fontSize}</span>
	       <button 
	       		id="increaseButton" 
	       		hidden={this.state.hidden}
	       		onClick={this.increase.bind(this)}
	       		disabled={this.state.fontSize >= this.props.max}
	       	>+</button>
	       <span
	       		style={
	       			{ fontWeight: this.state.checked ?  "bold" : "normal",
		       			fontSize: this.state.fontSize,

	       			 }
	       		}
	       		onClick={this.changeHidden.bind(this)}
	       		id="textSpan" 
	       	>{this.props.text}</span>
	       </div>
		);
    }
}




