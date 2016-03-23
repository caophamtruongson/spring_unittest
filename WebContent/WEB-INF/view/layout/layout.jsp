<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/resources/images/favicon.ico">

    <title>${title}</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap-theme.min.css" rel="stylesheet">

    <style>
      body { margin: 40px; }
    </style>
  </head>

  <body>
    <div class="container">
      <div class="col-md-12">
        <tiles:insertAttribute name="header" />
      </div>
      <div class="col-md-12">
         <tiles:insertAttribute name="body" /></div>
      </div>
      <footer>
        <tiles:insertAttribute name="footer" />
      </footer>
    </div>
  </body>
</html>