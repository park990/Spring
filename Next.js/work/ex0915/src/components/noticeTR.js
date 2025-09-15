export default function noticeTR(props){
    return(
        <tr>
                        <td>1</td>
                        <td>{props.subject}</td>
                        <td>{props.writer}</td>
                        <td>{props.date}</td>
                        <td>2</td>
        </tr>
    );
};