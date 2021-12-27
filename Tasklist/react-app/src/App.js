import logo from './logo.svg';
import './App.css';
import { Component } from 'react';

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

class TaskList extends Component {

  constructor(props) {
    super(props);
    this.state = {value: ''}
    this.tasks = []

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.deleteTask = this.deleteTask.bind(this);
    this.changeName = this.changeName.bind(this);
  }

  

  handleChange(event) {
    this.setState({value: event.target.value});
    // console.log(this.state.value)
  }

  handleSubmit(event) {
    // this.setState({value: event.target.value});
    // console.log(this.state.value)
    this.tasks.push(this.state.value);
    // console.log(this.tasks);
    event.preventDefault()
    this.setState({value: event.target.value});
  }

  deleteTask(event) {
    // console.log(event.target.value);
    // console.log(this.tasks);
    // console.log(this.tasks.indexOf(event.target.value))
    const index = this.tasks.indexOf(event.target.value);
    if (index > -1) {
      this.tasks.splice(index, 1);
    }
    this.setState({value: event.target.value});
  }

  changeName(event) {
    const index = this.tasks.indexOf(event.target.value);
    if (index > -1) {
      console.log(this.state);
      console.log(index);
      console.log(this.tasks[index]);
      // this.tasks[index] = event.target.value;
      // this.tasks.splice(index, 1);
      this.tasks.fill(this.state.value, index, 1);
      // this.tasks.splice(index, 1);
      console.log(this.tasks[index]);
      console.log(this.tasks);
    }
    event.preventDefault()
    this.setState({value: event.target.value});
  }

  render() {
    // console.log('START');
    const listTask = this.tasks.map((task) => 
    <li style={{margin: "10px", listStyleType: "none"}} key={task}>
      {task}
      <button style={{margin: "10px"}} value={task} onClick={this.deleteTask}>Delete</button>
      <button style={{margin: "10px"}} value={task} onClick={this.changeName}>Change</button>
    </li>)
    return (
      <div>
        <form onSubmit={this.handleSubmit} style={{margin: "100px"}}>
          Task: 
          <input type="text" style={{margin: "10px", padding: "10px", marginLeft: "20px"}} onChange={this.handleChange} value={this.state.value} ></input>
          <button type='submit' style={{margin: "10px"}}>Добавить задачу</button>
        </form>

        <ul>{listTask}</ul>

      </div>
    )
  }



  /*
  some = []
  constructor(props) {
    super(props);
    this.state = {value: ''};

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {
    
    this.some.push(this.state.value);
    console.log(this.some);
    alert('Отправленное имя: ' + this.state.value);
    event.preventDefault();
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label>
          Имя:
          <input type="text" value={this.state.value} onChange={this.handleChange} />
        </label>
        <input type="submit" value="Отправить" />
      </form>
    );
  }
*/

  

}

class App extends Component {
  render() {
    return (
      <div className="App">
        <TaskList/>
      </div>
    )
  }
}

export default App;
