import { Button, Col, Form } from 'react-bootstrap';
import React, { useRef } from 'react';

function SearchBar(props) {
    const { options = ['title', 'userId'] } = props;
    const searchTypeRef = useRef();
    const searchTextRef = useRef();
    const onSearchPost = () => {
        //todo
        console.log(searchTypeRef.current.value);
        console.log(searchTextRef.current.value);
    };

    return (
        <Form>
            <Form.Row>
                <Col>
                    <Form.Control as="select" ref={searchTypeRef}>
                        {options.map((x, i) => (
                            <option key={i}>{x}</option>
                        ))}
                    </Form.Control>
                </Col>

                <Col>
                    <Form.Control type="text" ref={searchTextRef} />
                </Col>

                <Col>
                    <Button onClick={onSearchPost}>search</Button>
                </Col>
            </Form.Row>
        </Form>
    );
}

export default SearchBar;
