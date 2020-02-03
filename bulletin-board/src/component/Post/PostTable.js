import { Table } from 'react-bootstrap';
import React from 'react';

// todo 컴포넌트 파일로 분리
function PostTableHeader(props) {
    const {
        data = ['id', 'title', 'date', 'userId', 'stars', 'views', 'replies'],
    } = props;

    return (
        <thead>
            <tr>
                {data.map((x, i) => (
                    <th key={i}>{x}</th>
                ))}
            </tr>
        </thead>
    );
}

function PostTableRow({ data }) {
    return (
        <tr>
            <td>{data.id}</td>
            <td>{data.title}</td>
            <td>{data.date}</td>
            <td>{data.userId}</td>
            <td>{data.stars}</td>
            <td>{data.views}</td>
            <td>{data.replies}</td>
        </tr>
    );
}

function PostTableBody({ data = [] }) {
    return (
        <tbody>
            {data.map((row, i) => (
                <PostTableRow data={row} key={i} />
            ))}
        </tbody>
    );
}

export function PostTable(props) {
    const { posts } = props;
    
    //todo 테이블 너비 조정하기

    return (
        <Table striped bordered hover size="sm">
            <PostTableHeader />
            <PostTableBody data={posts} />
        </Table>
    );
}
