<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html> 
			<head>
				<style type="text/css">
					header {
						padding-top: 40px;
						padding-bottom: 40px;
						font-family: 'Architects Daughter', 'Helvetica Neue', Helvetica, Arial, serif;
						background: #2e7bcf url('data:image/jpeg;base64,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') 0 0 repeat-x;
						border-bottom: solid 1px #275da1;
					}
					a {
						text-decoration: none;
						color: #2879d0;
						background-color: transparent;
					}
					header h1 {
						width: 540px;
						margin-top: 0;
						margin-bottom: 0.2em;
						font-size: 72px;
						font-weight: normal;
						line-height: 1;
						color: #fff;
						letter-spacing: -1px;
					}
					header h2 {
						width: 540px;
						margin-top: 0;
						margin-bottom: 0;
						font-size: 26px;
						font-weight: normal;
						line-height: 1.3;
						color: #9ddcff;
						letter-spacing: 0;
					}
					th {
						padding-top: 12px;
						padding-bottom: 12px;
						text-align: left;
						background-color: cornflowerblue;
						color: white;
						border: 1px solid #ddd;
						padding: 8px;
					}
					td {
						padding-top: 12px;
						padding-bottom: 12px;
						text-align: left;
						border: 1px solid #ddd;
						padding: 8px;
					}
					header td {
						border: 0px;
					}
				</style>
			</head>
			<body>
				<header>
					<div class="inner">
						<table>
							<tr>
								<td width="15%"></td>
								<td width="85%">
									<a href="https://ravaan-techky.github.io/sonar-plugins/">
										<h1><i>Ravaan Techky</i></h1>
									</a>
								</td>
							</tr>
							<tr>
								<td colspan="2" >
									<h2><i>Sonar Issue Reports</i></h2>
								</td>
							</tr>
						</table>
					</div>
				</header>
				<table>
					<tr>
						<th style="text-align:left">Project</th>
						<th style="text-align:left">Issue Description</th>
						<th style="text-align:left">Last Updated By</th>
						<th style="text-align:left">Severity</th>
						<th style="text-align:left">Issue Category</th>
						<th style="text-align:left">Issue Status</th>
					</tr>
					<xsl:for-each select="root/array">
						<tr>
							<td><xsl:value-of select="project"/></td>
							<td><xsl:value-of select="message"/></td>
							<td><xsl:value-of select="substring-before(updateDate, 'T')"/></td>
							<td><xsl:value-of select="severity"/></td>
							<td>
								<xsl:for-each select="tags">
									<xsl:value-of select="."/>,
								</xsl:for-each>
							</td>
							<td><xsl:value-of select="status"/></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
