import _ from "lodash";
import React from "react";

function ReplyDepthsPadding({depth = 0}) {
	return (
		<>
			{_.range(depth).map((x, i) => (
				<p key={i}>-></p>
			))}
		</>
	);
}


export default ReplyDepthsPadding