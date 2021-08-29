import Product from '~/model/product'

const beanies: Array<Product> = [
  {
    id: 'R4OuKi9VnVwMKy321ikA',
    category: 'beanies',
    name: 'habitasse platea',
    colors: ['Khaki', 'Fuscia'],
    price: 638,
    manufacturer: 'nascetur',
    availability: 'InStock',
  },
  {
    id: '6rCPg5CcqFJcpAWZDXU0',
    category: 'beanies',
    name: 'mattis egestas',
    colors: ['Pink', 'Fuscia', 'Crimson'],
    price: 614,
    manufacturer: 'aenean',
    availability: 'InStock',
  },
  {
    id: 'GgFnGfzmkZatPp6Sm17p',
    category: 'beanies',
    name: 'mauris eget massa',
    colors: ['Purple'],
    price: 305,
    manufacturer: 'tincidunt',
    availability: 'OutOfStock',
  },
  {
    id: 'chUDARuvQnB021wTfEeO',
    category: 'beanies',
    name: 'libero quis',
    colors: ['Puce', 'Puce'],
    price: 528,
    manufacturer: 'tortor',
    availability: 'LessThan10',
  },
  {
    id: 'DenjHhqoM2UXePNNY0y1',
    category: 'beanies',
    name: 'turpis elementum ligula',
    colors: ['Khaki'],
    price: 426,
    manufacturer: 'justo',
    availability: 'InStock',
  },
  {
    id: '1XexnO7fo9opvgtCWAFE',
    category: 'beanies',
    name: 'nam dui',
    colors: ['Teal'],
    price: 161,
    manufacturer: 'ullamcorper',
    availability: 'OutOfStock',
  },
  {
    id: 'TzpkfyvDkthZmmgq62S6',
    category: 'beanies',
    name: 'diam erat',
    colors: ['Puce', 'Teal'],
    price: 585,
    manufacturer: 'interdum',
    availability: 'InStock',
  },
  {
    id: 'iMmLiRxw6tQ3eGeCO9I0',
    category: 'beanies',
    name: 'pede posuere',
    colors: ['Mauv', 'Yellow'],
    price: 540,
    manufacturer: 'orci',
    availability: 'OutOfStock',
  },
  {
    id: 'R00IGqYRYA6OEJyzmQOh',
    category: 'beanies',
    name: 'volutpat in congue',
    colors: ['Red', 'Yellow', 'Violet'],
    price: 148,
    manufacturer: 'nonummy',
    availability: 'InStock',
  },
  {
    id: 'r5fD2JsKobkwAOQ7yMLX',
    category: 'beanies',
    name: 'ut erat',
    colors: ['Orange', 'Violet', 'Indigo'],
    price: 122,
    manufacturer: 'condimentum',
    availability: 'OutOfStock',
  },
]

const facemasks: Array<Product> = [
  {
    id: 'nHqm5T0en3sLPIJRNlJ6',
    category: 'facemasks',
    name: 'justo maecenas rhoncus',
    colors: ['Pink', 'Blue'],
    price: 530,
    manufacturer: 'donec',
    availability: 'LessThan10',
  },
  {
    id: 'jyhExPtx33ydR5FiMWOY',
    category: 'facemasks',
    name: 'est quam pharetra',
    colors: ['Puce'],
    price: 127,
    manufacturer: 'praesent',
    availability: 'OutOfStock',
  },
  {
    id: 'IZYb5mL3eZVll4iPFEde',
    category: 'facemasks',
    name: 'donec ut',
    colors: ['Violet'],
    price: 547,
    manufacturer: 'praesent',
    availability: 'LessThan10',
  },
  {
    id: 'dUOP1eicICrMCMCyd6hl',
    category: 'facemasks',
    name: 'aenean fermentum',
    colors: ['Puce', 'Yellow'],
    price: 345,
    manufacturer: 'luctus',
    availability: 'InStock',
  },
  {
    id: 'qfzVoDwwmluXicU1y6fI',
    category: 'facemasks',
    name: 'id pretium iaculis',
    colors: ['Violet', 'Red', 'Goldenrod'],
    price: 591,
    manufacturer: 'vulputate',
    availability: 'LessThan10',
  },
  {
    id: 'ndAm22feOzop6nwhrIo4',
    category: 'facemasks',
    name: 'volutpat in congue',
    colors: ['Mauv', 'Orange'],
    price: 457,
    manufacturer: 'a',
    availability: 'LessThan10',
  },
  {
    id: 'G7jzohjttBIgvRyFdEJb',
    category: 'facemasks',
    name: 'ut suscipit',
    colors: ['Maroon', 'Maroon', 'Khaki'],
    price: 282,
    manufacturer: 'nulla',
    availability: 'LessThan10',
  },
  {
    id: 'bcH12ns5CRqCDpJMISiW',
    category: 'facemasks',
    name: 'sed accumsan felis',
    colors: ['Fuscia'],
    price: 28,
    manufacturer: 'nisi',
    availability: 'LessThan10',
  },
  {
    id: '2TcocNl12AQ5pNi9tZFq',
    category: 'facemasks',
    name: 'velit donec diam',
    colors: ['Green', 'Green', 'Green'],
    price: 178,
    manufacturer: 'sapien',
    availability: 'InStock',
  },
  {
    id: 'ypZ6BX71YRRGcHpu8S0D',
    category: 'facemasks',
    name: 'diam id',
    colors: ['Turquoise', 'Red', 'Green'],
    price: 348,
    manufacturer: 'vestibulum',
    availability: 'LessThan10',
  },
]

const gloves: Array<Product> = [
  {
    id: 'T6Ak46JHX9gt5UndG9KR',
    category: 'gloves',
    name: 'morbi quis tortor',
    colors: ['Fuscia', 'Orange', 'Violet'],
    price: 673,
    manufacturer: 'eget',
    availability: 'OutOfStock',
  },
  {
    id: '8GDB8vrt8RN1APWzIxmo',
    category: 'gloves',
    name: 'vivamus tortor',
    colors: ['Violet'],
    price: 459,
    manufacturer: 'feugiat',
    availability: 'OutOfStock',
  },
  {
    id: '8D1a1gXfu8fBd1tkNnDS',
    category: 'gloves',
    name: 'in hac',
    colors: ['Crimson', 'Pink', 'Red'],
    price: 491,
    manufacturer: 'in',
    availability: 'LessThan10',
  },
  {
    id: 'PETAQXFOQdwvOLWrx66O',
    category: 'gloves',
    name: 'urna pretium nisl',
    colors: ['Pink', 'Yellow'],
    price: 657,
    manufacturer: 'lectus',
    availability: 'LessThan10',
  },
  {
    id: 'KnpHSZI1qOnOQDQvaSn9',
    category: 'gloves',
    name: 'porttitor lacus',
    colors: ['Mauv', 'Crimson', 'Pink'],
    price: 172,
    manufacturer: 'praesent',
    availability: 'InStock',
  },
  {
    id: 'O6cT8Jcrh57oWygXC02C',
    category: 'gloves',
    name: 'vivamus metus',
    colors: ['Mauv', 'Aquamarine', 'Turquoise'],
    price: 417,
    manufacturer: 'orci',
    availability: 'LessThan10',
  },
  {
    id: 'hC7wz59gTw9ZPcCTgTpZ',
    category: 'gloves',
    name: 'interdum eu',
    colors: ['Purple'],
    price: 319,
    manufacturer: 'tellus',
    availability: 'LessThan10',
  },
  {
    id: 'u19XMwOo1mvn0ERoGPUd',
    category: 'gloves',
    name: 'cubilia curae',
    colors: ['Yellow', 'Mauv', 'Violet'],
    price: 193,
    manufacturer: 'pede',
    availability: 'InStock',
  },
  {
    id: 'aCacNB4yqHD3lgwmF7T3',
    category: 'gloves',
    name: 'arcu libero rutrum',
    colors: ['Crimson', 'Aquamarine'],
    price: 98,
    manufacturer: 'varius',
    availability: 'OutOfStock',
  },
  {
    id: 'XtZSNh7TN9sIIvRPhG1Y',
    category: 'gloves',
    name: 'donec semper sapien',
    colors: ['Pink'],
    price: 550,
    manufacturer: 'mattis',
    availability: 'InStock',
  },
]

export { beanies, facemasks, gloves }