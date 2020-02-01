import PostEditor from '../component/Post/PostEditor.js';
import React from 'react';
import {Container, Row} from 'react-bootstrap';

function PostEditorPage(props) {
	const {post = {}} = props;
	return (
		<Container>
			<Row>
				<PostEditor post={post}/>
			</Row>
		</Container>
	);
}

export default PostEditorPage;
