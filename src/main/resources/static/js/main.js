var app = new Vue({
    el: '#app',
    data: {
        rows: [],
        cols: [],
        isCellCovered: true,
        isCellUncovered: false,
        displayText: ''
    },
    methods: {
        uncoverCell(idx1, idx2) {
            this.rows[idx1][idx2].isCellCovered = false
            this.rows[idx1][idx2].isCellUncovered = true
            this.rows[idx1][idx2].isCellMarked = false
            if (this.rows[idx1][idx2].value == '9') {
                this.rows[idx1][idx2].showed = '💥';
            }
            else if (this.rows[idx1][idx2].value == '0') {
                this.rows[idx1][idx2].showed = '';
            }
            else {
                this.rows[idx1][idx2].showed = this.rows[idx1][idx2].value;
            }
        },
        rightClickCell(idx1, idx2) {
            if (this.rows[idx1][idx2].isCellCovered) {
                this.rows[idx1][idx2].isCellMarked = true
                this.rows[idx1][idx2].isCellCovered = false
                this.rows[idx1][idx2].isCellUncovered = false
            }
            else if (this.rows[idx1][idx2].isCellMarked) {
                this.rows[idx1][idx2].isCellMarked = false
                this.rows[idx1][idx2].isCellCovered = true
                this.rows[idx1][idx2].isCellUncovered = false
            }
        }
    },
    mounted() {
        axios.get('http://localhost:8080/board/create')
            .then(response => (
                this.rows = response.data.map(row => (
                    this.cols = row.map (col => (
                       { showed:'', value: col, isCellCovered: true, ...col }
                    ))
                ))
            ))
    }
})


