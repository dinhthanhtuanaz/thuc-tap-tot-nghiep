<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quản trị Website - TiTi</title>
  <link href="{{ asset('public') }}/libs/fonts/fonts.css" rel="stylesheet">
  <link rel="stylesheet" href="{{ asset('public') }}/libs/fontawesome/css/all.css">
  <link rel="stylesheet" href="{{ asset('public') }}/libs/bootstrap/bootstrap.min.css">
  <link rel="stylesheet" href="{{ asset('public') }}/style.css">
  <link rel="stylesheet" href="{{ asset('public') }}/tuan-style.css">
</head>
@yield('main')
<script src="{{ asset('public') }}/libs/jquery/jquery-2.2.4.js"></script>
<script src="{{ asset('public') }}/libs/jquery/popper.min.js"></script>
<script src="{{ asset('public') }}/libs/bootstrap/bootstrap.min.js"></script>

{{-- counter jquery --}}
<script src="{{ asset('public') }}/libs/counter/waypoint.js"></script>
<script src="{{ asset('public') }}/libs/counter/jquery.counterup.min.js"></script>
{{-- piechart js --}}
<script src="{{ asset('public') }}/libs/piechart-mater/rpie.js"></script>

<script src="{{ asset('public') }}/script.js"></script>
@yield('block_chartjs')
